public class Criptografia {

    // Método para criptografar o texto com a cifra de César
    public static String criptografar(String texto, int chave) {
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char letraCriptografada = (char) ((c - base + chave) % 26 + base);
                resultado.append(letraCriptografada);
            } else {
                resultado.append(c); // Mantém caracteres que não são letras
            }
        }

        return resultado.toString();
    }

    // Método para descriptografar
    public static String descriptografar(String textoCriptografado, int chave) {
        return criptografar(textoCriptografado, 26 - chave);
    }

    public static void main(String[] args) {
        String mensagemOriginal = "Mensagem secreta";
        int chave = 3;

        String mensagemCriptografada = criptografar(mensagemOriginal, chave);
        System.out.println("Criptografada: " + mensagemCriptografada);

        String mensagemDescriptografada = descriptografar(mensagemCriptografada, chave);
        System.out.println("Descriptografada: " + mensagemDescriptografada);
    }
}