package com.example.demo.config;



public class SoapXml {

    public void generateSOAPXML(){

        private static String serverURI = "";
        System.setProperty("javax.xml.soap.SOAPFactory","org.apache.axis.soap.SOAPFactoryImpl");
        System.setProperty("javax.xml.soap.MessageFactory","org.apache.axis.MessageFactoryImpl");
        System.setProperty("javax.xml.soap.SOAPConnectionFactory","org.apache.axis.soap.SOAPConnectionFactoryImpl");

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage soapMessage = createSOAPRequest();
        SOAPMessage soapResponse = soapConnection.call(soapMessage,new URL(serverURI));

        SOAPBody sb = soapResponse.getSOAPBody();
        Iterator iterator = sb.getChildElements();
        SOAPBodyElement bodyElement = null;
        while(iterator.hasNext()){
            bodyElement = (SOAPBodyElement)iterator.next();
        }
    }

    private void buildEnvelope(SOAPEnvelope envelope){
        envelope.setPrefix("soapenv");
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        envelope.getBody().setprefix("soapenv");
        envelope.getHeaders().setPrefix("soapenv");
        envelope.setAttribute("xmlns:bsvc","urn:com.workday/bsvc");
        envelope.setAttribute("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("bsvc",serverURI);

    }

    private void buildHeaders(SOAPEnvelope envelope){
        SOAPHeader soapHeader = envelope.getHeader();
        SOAPElement security = header.addChildElement("security","wsse","namespaceURI");
        security.addAttribute(envelope.createName("userName","soapenv","soapNamespace"),"1");
        security.addAttribute(envelope.createName("userName","soapenv","soapNamespace"),"1");

        //same like that add the element


    }

    private SOAPMessage createSOAPRequest(){
        SOAPMessage soapMessage = null;
        MessageFactory messageFactory = MessageFactory.newInstance();
        soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envolpe =soapPart.getEnvelope();
        buildEnvelope(envelope);
        buildHeaders(envelope);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement element = soapBody.addChildElement("hire_employee_request","bsvc");
        element.setAttribute("bsvc:version","V29.0");

        SOAPElement element1 = element.addChildElement("Business_Process_parameters","bsvc")
        element1.addChildElement("Auto_complete","bsvc").addTExtNode("1");
        element1.addChildElement("Run_Now","bsvc").addTExtNode("1");

        //Like this add nodes
    }

}
