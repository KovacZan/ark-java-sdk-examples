import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;

import java.io.IOException;
import java.util.ArrayList;

public class Helper {
    public static long getNonce(Connection connection, String sended) throws IOException {
        // wait to be forged
        LinkedTreeMap<String, Object> actual = connection.api().wallets.sentTransactions(sended);
//        return Long.valueOf(((LinkedTreeMap<String, Object>) connection.api().wallets.show(sended).get("data")).get("nonce"));
        return Long.valueOf (((LinkedTreeMap<String, Object>) connection.api().wallets.show(sended).get("data")).get("nonce").toString()) + 1;
    }

}
