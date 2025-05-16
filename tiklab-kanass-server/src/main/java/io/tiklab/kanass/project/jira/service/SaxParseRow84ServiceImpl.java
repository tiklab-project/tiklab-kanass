package io.tiklab.kanass.project.jira.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;


// 自定义的事件处理类
class SaxParseRow84ServiceImpl extends DefaultHandler {

    boolean inRow = false;
    boolean isSprint = false;

    boolean isSprintApplication = false;
    int rowNum = 0;
    String currentElement = "";

    boolean isIntegerTag = false;
    boolean isBooleanTag = false;
    boolean isStringTag = false;

    private static Element globalElement;
    ArrayList<Element> ElementList = new ArrayList<Element>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName; // 获取当前元素的名称
        String value = attributes.getValue(0);

        if (value != null && "data".equals(qName) && value.equals("AO_60DB71_SPRINT")) {
            isSprint = true;
        }
        if (qName.equals("row") && isSprint) {
            inRow = true;
            rowNum = 0;
            System.out.println("Start a new row:");
        }
        if(isSprintApplication){
            rowNum = rowNum + 1;
            System.out.println("flg:" + qName + "------" + rowNum);
        }
        if(inRow && qName.equals("boolean") && rowNum == 0){
           isSprintApplication = true;
        }

        if (isSprintApplication && qName.equals("integer")) {
            isIntegerTag = true;
        } else if (isSprintApplication &&  qName.equals("boolean")) {
            isBooleanTag = true;
        } else if (isSprintApplication &&  qName.equals("string")) {
            isStringTag = true;
        }

    }
    public void createElement(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            globalElement = document.createElement("Sprint");

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isSprintApplication) {
            String content = new String();
            if(rowNum == 0){
                content = new String(ch, start, length);
                if (!content.isBlank()) {
                    createElement();
                    globalElement.setAttribute("closed", content);
                    System.out.println(currentElement + ": " + content);
                    isBooleanTag = false;
                }
            }else {
                if (isIntegerTag) {
                    content = new String(ch, start, length);
                    if(!content.isEmpty()){
                        System.out.println(currentElement  + ":" +  content);
                    }
                    isIntegerTag = false;
                } else if (isBooleanTag) {
                    content = new String(ch, start, length);
                    System.out.println(currentElement  + ":" + content);
                    isBooleanTag = false;
                } else if (isStringTag) {
                    content = new String(ch, start, length).trim();
                    if (content.isEmpty()) {
                        System.out.println(currentElement + ":" + "String value is empty");
                    } else {
                        System.out.println(currentElement  + ":" +  content);
                    }
                    isStringTag = false;
                }
                if (!content.isEmpty()) {
                    switch (rowNum){
//                        case 0:
//                            globalElement.setAttribute("closed", content);
//                            break;
                        case 1:
                            globalElement.setAttribute("complete_date", content);
                            break;
                        case 2:
                            globalElement.setAttribute("end_date", content);
                            break;
                        case 4:
                            globalElement.setAttribute("id", content);
                            break;
                        case 5:
                            globalElement.setAttribute("name", content);
                            break;
                        case 6:
                            globalElement.setAttribute("rapid_view_id", content);
                            break;
                        case 8:
                            globalElement.setAttribute("started", content);
                            break;
                        case 9:
                            globalElement.setAttribute("start_date", content);
                            break;
                        default:
                            break;
                    }
                    System.out.println(currentElement + ": " + content);
                }
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("row") && isSprintApplication){
            inRow = false;
            isSprintApplication = false;
            rowNum = 0;
            ElementList.add(globalElement);
        }
        if ("data".equals(qName) && isSprint) {
            isSprint = false;
        }

    }

    public ArrayList<Element> getElementList(){
        return ElementList;
    }
}