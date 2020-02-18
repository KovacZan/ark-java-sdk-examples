import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.networks.Devnet;
import org.arkecosystem.crypto.transactions.Transaction;
import org.arkecosystem.crypto.transactions.builder.HtlcRefund;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HtlcRefundTransaction {
    public static void main(String[] args) throws IOException {
        // Set the network
        Network.set(new Devnet());
        // Make configurations and connect to the node
        HashMap<String, Object> configurations = new HashMap<>();
        configurations.put("host", "https://dexplorer.ark.io/api/");
        configurations.put("content-type","application/json");
        Connection connection = new Connection(configurations);

        // Retrieve the nonce
        long nonce = Long.parseLong(((LinkedTreeMap<String, Object>) connection.api()
                .wallets
                .show("D8rr7B1d6TL6pf14LgMz4sKp1VBMs6YUYD")
                .get("data"))
                .get("nonce").toString());
        // Increment it by one
        nonce++;

        // Create the transaction
        Transaction actual = new HtlcRefund()
                .htlcRefundAsset("943c220691e711c39c79d437ce185748a0018940e1a4144293af9d05627d2eb4")
                .nonce(nonce)
                .sign("master dizzy era math peanut crew run manage better flame tree prevent")
                .transaction;

        // Add transaction to payload
        ArrayList<HashMap> payload = new ArrayList<>();
        payload.add(actual.toHashMap());

        // Broadcast the transaction
        LinkedTreeMap<String, Object> broadcastResponse = connection.api().transactions.create(payload);

        // Log the response
        System.out.println(broadcastResponse);

    }
}
