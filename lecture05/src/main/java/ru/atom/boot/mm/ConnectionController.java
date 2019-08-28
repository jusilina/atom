package ru.atom.boot.mm;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.atom.thread.mm.Connection;
import ru.atom.thread.mm.ConnectionQueue;

import java.util.concurrent.atomic.AtomicReference;


@Controller
@RequestMapping("/connection")
public class ConnectionController {
    private static final Logger log = LogManager.getLogger(ConnectionController.class);


    /**
     * curl test
     *
     * curl -i -X POST -H "Content-Type: application/x-www-form-urlencoded" \
     * localhost:8080/connection/connect -d 'id=1&name=bomberman'
     * curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'name=bomberman&id=123' http://localhost:8080/connection/connect
     */
    @RequestMapping(
            path = "connect",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void connect(@RequestParam("id") long id,
                        @RequestParam("name") String name) {

        log.info("New connection id={} name={}", id, name);
        ConnectionQueue.getInstance().offer(new Connection(id, name));

        String list = ConnectionQueue.getInstance().toString();
        log.info("Offer list={} ", list);

    }

    /**
     * curl test
     *
     * curl -i localhost:8080/connection/list'
     * @return
     */
    @RequestMapping(
            path = "list",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> list() {
        log.info("Connections list request");
        final String[] list = {""};
         ConnectionQueue.getInstance().parallelStream().forEach(connection -> {
             list[0] += connection.toString();});
//       String responseBody = String.join("\n", ConnectionQueue.getInstance().stream().sorted().collect(Connection.toString()));
        return ResponseEntity.ok(list[0]);
      //  throw new UnsupportedOperationException();
    }


}
