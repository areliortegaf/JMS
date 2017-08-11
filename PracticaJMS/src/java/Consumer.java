
import static com.sun.faces.facelets.util.Path.context;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;

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
public class Consumer {

    private static final Logger logger = Logger.getLogger("com.pack.clase");

    @Inject
    private JMSContext context;
    @Resource(lookup = "jms/miQueue")
    private Queue queue;

    public Consumer() {
    }

    public void recibirMensaje() {
        try (JMSConsumer consumer = context.createConsumer((Destination) queue)) {
            Message m = consumer.receive(1000);
            if (m != null) {
                logger.log(Level.INFO, "Recieved Message" + m.getBody(String.class));

            } else {
                logger.log(Level.INFO, "Mensaje no recibido");
            }
        } catch (JMSException e) {
            logger.log(Level.INFO, "Exception " + e);
        }
    }
}
