import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.identities.Address;
import org.arkecosystem.crypto.networks.Devnet;
import org.arkecosystem.crypto.networks.Testnet;
import org.arkecosystem.crypto.transactions.Transaction;
import org.arkecosystem.crypto.transactions.builder.Transfer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TransferTransaction {
    public static Transaction CreateTransferTransaction(int amount, String recipientAddress, String passphrase, long nonce) {
        Transaction actual = new Transfer()
                .recipient(recipientAddress)
                .amount(amount)
                .nonce(nonce)
                .vendorField("Java \uD83D \uDD31 \uD83C \uDF7A")
                .sign(passphrase)
                .transaction;

        return actual;
    }

    public static void main(String[] args) throws IOException {
        Network.set(new Devnet());
        HashMap<String, Object> map = new HashMap<>();
        map.put("host", "http://137.74.27.246:4003/api/"); // network settings are autoc-configured from the node
        map.put("content-type","application/json");

        Connection connection2 = new Connection(map);
        long nonce = Helper.getNonce(connection2,"D8rr7B1d6TL6pf14LgMz4sKp1VBMs6YUYD");
        ArrayList<HashMap> payload = new ArrayList<>();
        Transaction transfer1 =
                CreateTransferTransaction(10,
                        "D8rr7B1d6TL6pf14LgMz4sKp1VBMs6YUYD",
                        "master dizzy era math peanut crew run manage better flame tree prevent",
                        nonce
                );
        payload.add(transfer1.toHashMap());

        for (int i = 1; i < 20 ; i++) {
            Transaction transfer2 =
                    CreateTransferTransaction(10+i,
                            "D8rr7B1d6TL6pf14LgMz4sKp1VBMs6YUYD",
                            "master dizzy era math peanut crew run manage better flame tree prevent",
                            nonce + i
                    );
            payload.add(transfer2.toHashMap());
        }

        LinkedTreeMap<String, Object> postResponse = connection2.api().transactions.create(payload);
        System.out.println(postResponse);


    }
}
