import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.identities.Address;
import org.arkecosystem.crypto.networks.Testnet;
import org.arkecosystem.crypto.transactions.Transaction;
import org.arkecosystem.crypto.transactions.builder.SecondSignatureRegistration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SecondSignatureTransaction {
    public static Transaction CreateSecondSignatureTransaction(String passphrase, long nonce) {
        Transaction actual = new SecondSignatureRegistration()
                .signature("clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire")
                .nonce(nonce)
                .sign(passphrase)
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

        Transaction transfer1 = CreateSecondSignatureTransaction("clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire",Helper.getNonce(connection2, Address.fromPassphrase("clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire")));
        payload.add(transfer1.toHashMap());
        LinkedTreeMap<String, Object> postResponse = connection2.api().transactions.create(payload);
        System.out.println(postResponse);
    }
}
