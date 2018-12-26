/**
 * WSServerServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package gr.xxm.helper;

import gr.xxm.helper.WSServerServiceStub.HelloResponse;
import gr.xxm.helper.WSServerServiceStub.MainResponse;

/**
 *  WSServerServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class WSServerServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public WSServerServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public WSServerServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for hello method
     * override this method for handling normal response from hello operation
     */
    public void receiveResulthello(
        HelloResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from hello operation
     */
    public void receiveErrorhello(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for main method
     * override this method for handling normal response from main operation
     */
    public void receiveResultmain(
        MainResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from main operation
     */
    public void receiveErrormain(java.lang.Exception e) {
    }
}
