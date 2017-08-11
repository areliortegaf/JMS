
import java.util.Queue;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asortega
 */
@RequestScoped
public class Producer {

    @Inject
    private JMSContext context;
    @Resource(lookup = "jms/myQueue")
    private Queue queue;

    public Producer() {
    }

    public void mandarMensaje(String Mensaje) {
        context.createProducer().send((Destination) queue, Mensaje);
    }

}
