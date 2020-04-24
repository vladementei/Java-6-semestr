package service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * <b>Class assistant to switch {@link RemoteService remote servie} for different databases on server</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RemoteServiceController {
    /**
     * contains definition of methods on server (will be initialized with plug)
     */
    private static RemoteService remoteService;

    /**
     * default constructor
     */
    private RemoteServiceController(){
    }

    /**
     * getter of {@link RemoteService remote service}
     * @return instance of current {@link RemoteService remote service}
     */
    public static RemoteService getService() {
        return remoteService;
    }

    /**
     * setter of {@link RemoteService remote service}
     * @param remoteService new {@link RemoteService remote service} instance
     */
    public static void setRemoteService (RemoteService remoteService) {
        RemoteServiceController.remoteService = remoteService;
    }

    /**
     * setter of {@link RemoteService remote service} by searching new one
     * @param url path to server plug to make connection
     * @throws RemoteException if connection failed
     */
    public static void setRemoteService (String url) throws RemoteException{
        try {
            Context context = new InitialContext();
            remoteService = (RemoteService)context.lookup(url);
        } catch (NamingException e) {
            throw new RemoteException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
