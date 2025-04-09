import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CriptografiaAES {

    // Gera uma chave secreta AES de 128 bits
    public static SecretKey gerarChave() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Pode ser 128, 192 ou 256, dependendo do suporte da JRE
        return keyGen.generateKey();
    }

    // Criptografa o texto usando a chave fornecida
    public static String criptografar(String texto, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCriptografado);
    }

    // Descriptografa o texto usando a chave fornecida
    public static String descriptografar(String textoCriptografado, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] bytesDescriptografados = cipher.doFinal(Base64.getDecoder().decode(textoCriptografado));
        return new String(bytesDescriptografados);
    }

    public static void main(String[] args) {
        try {
            String mensagem = "Isso é um segredo!";

            // Gera a chave AES
            SecretKey chave = gerarChave();

            // Criptografa a mensagem
            String mensagemCriptografada = criptografar(mensagem, chave);
            System.out.println("Criptografado: " + mensagemCriptografada);

            // Descriptografa a mensagem
            String mensagemDescriptografada = descriptografar(mensagemCriptografada, chave);
            System.out.println("Descriptografado: " + mensagemDescriptografada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
