package service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class RemoteServiceController {
    private static RemoteService remoteService;

    private RemoteServiceController(){
    }

    public static RemoteService getService() {
        return remoteService;
    }

    public static void setRemoteService (RemoteService remoteService) {
        RemoteServiceController.remoteService = remoteService;
    }

    public static void setRemoteService (String url) throws RemoteException{
        try {
            Context context = new InitialContext();
            remoteService = (RemoteService)context.lookup(url);
        } catch (NamingException e) {
            throw new RemoteException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
