from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Random import get_random_bytes
import binascii

# Função para gerar as chaves pública e privada
def gerar_chaves():
    chave = RSA.generate(2048)  # Gera a chave RSA de 2048 bits
    chave_privada = chave.export_key()
    chave_publica = chave.publickey().export_key()
    return chave_privada, chave_publica

# Função para criptografar a mensagem com a chave pública
def criptografar_mensagem(mensagem, chave_publica):
    chave = RSA.import_key(chave_publica)
    cipher = PKCS1_OAEP.new(chave)
    mensagem_criptografada = cipher.encrypt(mensagem.encode())
    return binascii.hexlify(mensagem_criptografada)  # Retorna em formato hexadecimal para exibição

# Função para descriptografar a mensagem com a chave privada
def descriptografar_mensagem(mensagem_criptografada, chave_privada):
    chave = RSA.import_key(chave_privada)
    cipher = PKCS1_OAEP.new(chave)
    mensagem_criptografada = binascii.unhexlify(mensagem_criptografada)  # Converte de volta para bytes
    mensagem_descriptografada = cipher.decrypt(mensagem_criptografada).decode()
    return mensagem_descriptografada

# Gerando as chaves pública e privada
chave_privada, chave_publica = gerar_chaves()

# Exemplo de mensagem a ser criptografada
mensagem_original = "Mensagem secreta com criptografia assimétrica!"

# Criptografando a mensagem com a chave pública
mensagem_criptografada = criptografar_mensagem(mensagem_original, chave_publica)
print("Mensagem criptografada:", mensagem_criptografada)

# Descriptografando a mensagem com a chave privada
mensagem_descriptografada = descriptografar_mensagem(mensagem_criptografada, chave_privada)
print("Mensagem descriptografada:", mensagem_descriptografada)