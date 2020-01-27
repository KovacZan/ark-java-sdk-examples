import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.identities.Address;
import org.arkecosystem.crypto.networks.Testnet;
import org.arkecosystem.crypto.transactions.Transaction;
import org.arkecosystem.crypto.transactions.builder.Transfer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TransferTransaction {
    public static Transaction CreateDemoTransaction(int amount, String recipientAddress, String passphrase1) {
        Transaction actual = new Transfer()
                .recipient(recipientAddress)
                .amount(amount)
                .nonce(3)
                .vendorField("Java")
                .sign(passphrase1)
                .transaction;

        return actual;
    }
    public static void main(String[] args) throws IOException {
        Network.set(new Testnet());
        HashMap<String, Object> map = new HashMap<>();
         map.put("host", "http://localhost:4003/api/"); // network settings are autoc-configured from the node
        map.put("content-type","application/json");

        Connection connection2 = new Connection(map);
        ArrayList<HashMap> payload = new ArrayList<>();
        Transaction transfer1 = CreateDemoTransaction(100, "AbfQq8iRSf9TFQRzQWo33dHYU7HFMS17Zd", "clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire");
        payload.add(transfer1.toHashMap());
        LinkedTreeMap<String, Object> postResponse = connection2.api().transactions.create(payload);
        System.out.println(postResponse);


    }
}
