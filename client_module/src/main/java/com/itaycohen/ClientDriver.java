package com.itaycohen;

import com.itaycohen.controllers.DigitalLibraryController;
import com.itaycohen.data_layer.LibraryInternetWebService;
import com.itaycohen.data_layer.LibraryRepository;
import com.itaycohen.utils.GsonContainer;
import com.itaycohen.views.DashBoard;

import javax.swing.SwingUtilities;

public class ClientDriver {

    private static final int SERVER_PORT = 12345;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        LibraryInternetWebService service = new LibraryInternetWebService(
                SERVER_PORT,
                SERVER_HOST,
                GsonContainer.getInstance()
        );
        new DigitalLibraryController(new DashBoard(), new LibraryRepository(service));
    }
}
