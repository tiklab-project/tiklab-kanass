host=$host
port=$port
userName=$userName
password=$password
dbName=teamwire_ee
schemaName=$schemaName
prePath=$prePath
backupsSqlUrl=$backupsSqlUrl



pgsql_bin="/Library/PostgreSQL/15/bin"
psql="${pgsql_bin}/psql"


PGPASSWORD=${password} ${psql} -U ${userName} -h ${host} -p ${port} -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname='${dbName}';"
PGPASSWORD=${password} ${psql} -U ${userName} -h ${host} -p ${port} -c  "DROP DATABASE ${dbName};"
PGPASSWORD=${password} ${psql} -U ${userName} -h ${host} -p ${port} -c "CREATE DATABASE ${dbName};"


PGPASSWORD=${password} ${psql} -U ${userName} -h ${host} -p ${port} -d ${dbName}  -n ${schemaName}<${backupsSqlUrl}tiklab_teamwire.sql;
echo "执行dump完成"

#tar -zxvf ${reduceUrl} -C ${prePath}
#cp -r ${backupsCodeUrl}/* ${sourceFilePath}

#rm -rf ${backupsSqlUrl}

#create db





