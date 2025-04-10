import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@SuppressWarnings("unused")
public class CriptografiaAES {

    // Método para gerar uma chave AES de 128 bits
    public static SecretKey gerarChave() throws Exception {
        KeyGenerator gerador = KeyGenerator.getInstance("AES");
        gerador.init(128); // ou 192 ou 256 bits, dependendo do suporte da JVM
        return gerador.generateKey();
    }

    // Criptografa um texto usando AES
    public static String criptografar(String texto, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCriptografado);
    }

    // Descriptografa um texto AES
    public static String descriptografar(String textoCriptografado, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] textoDecodificado = Base64.getDecoder().decode(textoCriptografado);
        byte[] textoDescriptografado = cipher.doFinal(textoDecodificado);
        return new String(textoDescriptografado);
    }

    public static void main(String[] args) throws Exception {
        String mensagemOriginal = "Mensagem super secreta";

        // Gerando uma chave AES
        SecretKey chaveSecreta = gerarChave();

        // Criptografando
        String mensagemCriptografada = criptografar(mensagemOriginal, chaveSecreta);
        System.out.println("Criptografada: " + mensagemCriptografada);

        // Descriptografando
        String mensagemDescriptografada = descriptografar(mensagemCriptografada, chaveSecreta);
        System.out.println("Descriptografada: " + mensagemDescriptografada);
    }
}
