console.log('meu primeiro codigo js')
console.log('não é magia, é tecnologia')

//metodos disponiveis para serem acionados via eventos dos elementos da pagina
function cadastrar() {
    let nome = document.getElementById('nome').value
    let sobrenome = document.getElementById('sobrenome').value
    document.getElementById('mensagem').innerHTML = 'Bem-vindo ' + nome + ' ' + sobrenome;
}

//vincular os metodos aos elementos da pagina pelo id
document.getElementById('botaoCancelar').onclick = function() {
    alert('Como você faria para limpar ou restaurar a tela ?')
    console.log('é fundamental aprender os fundamentos de html e javascript')
};