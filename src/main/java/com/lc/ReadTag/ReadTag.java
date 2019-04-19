package com.lc.ReadTag;

@SuppressWarnings("all")
public class ReadTag {

    public String getMessageFromTagReturnData(String data,String tagName){

        if(tagName.contains("TaskInformation")){

            String[] taskInformation=data.split("<TaskInformation>");

            return "<TaskInformation>"+taskInformation[1].split("</TaskInformation>")[0]+"</TaskInformation>";

        }else if (tagName.contains("Maintransformer")){

            String[] maintransformer=data.split("<Maintransformer>");
            return "<Maintransformer>"+maintransformer[1].split("</Maintransformer>")[0]+"</Maintransformer>";
        }else if (tagName.contains("DistributionTransformer")){

            String[] distributionTransformer=data.split("<DistributionTransformer>");
            return "<DistributionTransformer>"+distributionTransformer[1].split("</DistributionTransformer>")[0]+"</DistributionTransformer>";
        }else if (tagName.contains("Line")){
            String[] line=data.split("<Line>");
                return "<Line>"+line[1].split("</line>")[0]+"</Line>";
        }

        return "标签：null异常";

    }

    public static void main(String[] args) {

        ReadTag readDTag = new ReadTag();
        ReadFile readFile= new ReadFile();

        System.out.println(readDTag.getMessageFromTagReturnData(readFile.ReadfileReturnString("E:\\测试文件.txt"),"DistributionTransformer"));




    }

}
