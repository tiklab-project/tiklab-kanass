host=$host
port=$port
userName=$userName
password=$password
dbName=$dbName
schemaName=$schemaName
backupsUrl=$backupsUrl


pgsql_bin="/Library/PostgreSQL/15/bin"
psql="${pgsql_bin}/psql"
pg_dump="${pgsql_bin}/pg_dump"
current_time="teamwire-backups-"$(date +%s)

PGPASSWORD=${password} ${pg_dump} -U ${userName} -h ${host} -p ${port} -d ${dbName} -n ${schemaName}>${backupsUrl}/${dbName}.sql


echo "执行dump完成"
#while read line ;  do
#cp -r ${line} ${backupsCodeUrl}
#done <<< ${sourceFilePath}

#tar -zcvf ${reduceName}.tar.gz  --strip-components=${length} ${backupsUrl}

#rm -rf ${backupsUrl}


#create db





