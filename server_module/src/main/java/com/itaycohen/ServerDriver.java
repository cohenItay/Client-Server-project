package com.itaycohen;

import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.algorithm.StringSearchFactory;
import com.itaycohen.dao.DaoFileImpl;
import com.itaycohen.dao.IDao;
import com.itaycohen.data_layer.dm.BookParams;
import com.itaycohen.data_layer.dm.IBook;
import com.itaycohen.server.HandleBookRequest;
import com.itaycohen.server.IHandleRequest;
import com.itaycohen.server.Server;
import com.itaycohen.services.BooksController;
import com.itaycohen.services.BooksService;
import com.itaycohen.services.IBooksService;
import com.itaycohen.services.IController;
import com.itaycohen.utils.GsonContainer;

import static com.itaycohen.algorithm.IStringSearchAlgoStrategy.Factory.PROPERTY_ASCENDING;
import static com.itaycohen.algorithm.IStringSearchAlgoStrategy.Factory.TYPE_KMP;

public class ServerDriver {

    public static final int PORT = 12345;

    public static void main(String[] args) {
        drive();
    }

    public static Server drive() {

        // Dependency injection :
        IStringSearchAlgoStrategy searchAlg = new StringSearchFactory().create(TYPE_KMP, PROPERTY_ASCENDING);
        IDao dao = new DaoFileImpl();
        IBooksService booksService = new BooksService(searchAlg, dao, GsonContainer.getInstance());
        IController<IBook[], BookParams[]> booksController = new BooksController(booksService);
        IHandleRequest handleRequest = new HandleBookRequest(GsonContainer.getInstance(), booksController);

        Server server = new Server(PORT, handleRequest);
        server.run();
        return server;
    }
}
