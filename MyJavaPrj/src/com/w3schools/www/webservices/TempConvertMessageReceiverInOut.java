
/**
 * TempConvertMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
        package com.w3schools.www.webservices;

        /**
        *  TempConvertMessageReceiverInOut message receiver
        */

        public class TempConvertMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutSyncMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        TempConvertSkeleton skel = (TempConvertSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("FahrenheitToCelsius".equals(methodName)){
                
                com.w3schools.www.webservices.FahrenheitToCelsiusResponse fahrenheitToCelsiusResponse1 = null;
	                        com.w3schools.www.webservices.FahrenheitToCelsius wrappedParam =
                                                             (com.w3schools.www.webservices.FahrenheitToCelsius)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.w3schools.www.webservices.FahrenheitToCelsius.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               fahrenheitToCelsiusResponse1 =
                                                   
                                                   
                                                         skel.FahrenheitToCelsius(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), fahrenheitToCelsiusResponse1, false);
                                    } else 

            if("CelsiusToFahrenheit".equals(methodName)){
                
                com.w3schools.www.webservices.CelsiusToFahrenheitResponse celsiusToFahrenheitResponse3 = null;
	                        com.w3schools.www.webservices.CelsiusToFahrenheit wrappedParam =
                                                             (com.w3schools.www.webservices.CelsiusToFahrenheit)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.w3schools.www.webservices.CelsiusToFahrenheit.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               celsiusToFahrenheitResponse3 =
                                                   
                                                   
                                                         skel.CelsiusToFahrenheit(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), celsiusToFahrenheitResponse3, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(com.w3schools.www.webservices.FahrenheitToCelsius param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.w3schools.www.webservices.FahrenheitToCelsius.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.w3schools.www.webservices.FahrenheitToCelsiusResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.w3schools.www.webservices.FahrenheitToCelsiusResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.w3schools.www.webservices.CelsiusToFahrenheit param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.w3schools.www.webservices.CelsiusToFahrenheit.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.w3schools.www.webservices.CelsiusToFahrenheitResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.w3schools.www.webservices.CelsiusToFahrenheitResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.w3schools.www.webservices.FahrenheitToCelsiusResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.w3schools.www.webservices.FahrenheitToCelsiusResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.w3schools.www.webservices.CelsiusToFahrenheitResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.w3schools.www.webservices.CelsiusToFahrenheitResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (com.w3schools.www.webservices.FahrenheitToCelsius.class.equals(type)){
                
                           return com.w3schools.www.webservices.FahrenheitToCelsius.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.w3schools.www.webservices.FahrenheitToCelsiusResponse.class.equals(type)){
                
                           return com.w3schools.www.webservices.FahrenheitToCelsiusResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.w3schools.www.webservices.CelsiusToFahrenheit.class.equals(type)){
                
                           return com.w3schools.www.webservices.CelsiusToFahrenheit.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.w3schools.www.webservices.CelsiusToFahrenheitResponse.class.equals(type)){
                
                           return com.w3schools.www.webservices.CelsiusToFahrenheitResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    