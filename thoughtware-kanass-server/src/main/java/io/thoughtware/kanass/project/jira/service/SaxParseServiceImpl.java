package io.thoughtware.kanass.project.jira.service;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SaxParseServiceImpl extends DefaultHandler  {
    private String preTag = null;

    ArrayList<Element> ElementList = new ArrayList<Element>();
    String JiraVersion = new String();
    String[] flags = {"User", "ApplicationUser", "Project", "Issue", "IssueLink","UserHistoryItem", "ProjectRole",
            "ProjectRoleActor", "IssueParentAssociation", "Status", "Version"};


    @Override
    public void startDocument() throws SAXException {
//        users = new ArrayList<User>();
        ElementList.clear();
        JiraVersion = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element element = document.createElement(qName);
            if(Arrays.asList(flags).contains(qName)){
                for (int i = 0; i < attributes.getLength(); i++) {
                    element.setAttribute(attributes.getQName(i), attributes.getValue(i));
                }
                ElementList.add(element);
            }
            if(qName.equals("PluginVersion") && StringUtils.isEmpty(JiraVersion)){
                for (int i = 0; i < attributes.getLength(); i++) {
                    element.setAttribute(attributes.getQName(i), attributes.getValue(i));
                }
                String name = element.getAttribute("name");
                if(name.equals("Jira Software Application")){
                    JiraVersion = element.getAttribute("version");
                    return;
                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        preTag = qName;//将正在解析的节点名称赋给preTag
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("Project".equals(qName)){
            for (Element attributes : ElementList) {
                String classAttr = attributes.getAttribute("name");
            }
        }

        preTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(preTag!=null){
            String content = new String(ch,start,length);
            if("project".equals(preTag)){
                System.out.println(content);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {

    }

    public ArrayList<Element> getElementList(){
        return ElementList;
    }
    public String getJiraVersion(){
        return JiraVersion;
    }
}
