def hash_simples(chave):
    hash_val = 0
    for char in chave:
        hash_val += ord(char)  # Obtém o valor ASCII do caractere
    return hash_val % 256  # Retorna o valor do hash com módulo 256

# Testando a função
chave = "exemplo"
print(f'O hash de "{chave}" é: {hash_simples(chave)}')