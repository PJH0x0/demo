#!/bin/bash
echo "build start"

JAR_PATH=libs
BIN_PATH=bin
SRC_PATH=src

# java文件列表目录
SRC_FILE_LIST_PATH=src/sources.list

#生所有的java文件列表
rm -f $SRC_PATH/sources
find $SRC_PATH/ -name *.java > $SRC_FILE_LIST_PATH

#删除就的编译文件 生成bin目录
rm -rf $BIN_PATH/
mkdir $BIN_PATH/

#生成依赖jar包 列表
for file in  ${JAR_PATH}/*.jar;
do
jarfile=${jarfile}:${file}
done
echo "jarfile = "$jarfile

#编译
javac -d $BIN_PATH/ -cp $jarfile @$SRC_FILE_LIST_PATH

echo "build end"

#运行
#java -cp $BIN_PATH$jarfile pers.algs._1._1.Practice
#java -cp $BIN_PATH$jarfile pers.algs._1._1.BinarySearch
#java -cp $BIN_PATH$jarfile pers.algs._1._2.Practice
#java -cp $BIN_PATH$jarfile pers.algs._1._3.Stack
#java -cp $BIN_PATH$jarfile pers.algs._1._3.Queue
java -cp $BIN_PATH$jarfile pers.algs._1._3.Practice

