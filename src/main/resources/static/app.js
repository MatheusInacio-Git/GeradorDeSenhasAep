document.addEventListener('DOMContentLoaded', () => {
    // Formulário de cadastro de usuário
    const formUsuario = document.getElementById('formUsuario');
    formUsuario.addEventListener('submit', async (event) => {
        event.preventDefault();

        const nomeUsuario = document.getElementById('nomeUsuario').value;
        const senhaUsuario = document.getElementById('senhaUsuario').value;

        // Verificar se a senha não está vazia
        if (!senhaUsuario || senhaUsuario.trim() === '') {
            alert('Por favor, insira uma senha.');
            return;
        }

        // Enviar dados para adicionar o usuário
        const response = await fetch('http://localhost:8080/usuarios', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome: nomeUsuario, senhaHash: senhaUsuario })
        });

        if (response.ok) {
            alert('Usuário cadastrado com sucesso!');
            formUsuario.reset();
        } else {
            alert('Erro ao cadastrar usuário');
        }
    });

    // Formulário de cadastro de senha
    const formSenha = document.getElementById('formSenha');
    formSenha.addEventListener('submit', async (event) => {
        event.preventDefault();

        const idUsuario = document.getElementById('idUsuario').value;
        const descricaoSenha = document.getElementById('descricaoSenha').value;
        const senha = document.getElementById('senha').value;
        const nome = document.getElementById('nome').value

        // Verificar se a senha não está vazia
        if (!senha || senha.trim() === '') {
            alert('Por favor, insira uma senha.');
            return;
        }

        // Enviar dados para adicionar a senha
        const response = await fetch('http://localhost:8080/senhas', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                descricao: descricaoSenha,
                hashSenha: senha,  // Aqui estamos enviando a senha em hash
                usuario: {
                    idUsuario: idUsuario  // Dentro do objeto "usuario", com idUsuario
                },
                nome: nome
            })
        });

        if (response.ok) {
            alert('Senha cadastrada com sucesso!');
            formSenha.reset();
        } else {
            alert('Erro ao cadastrar senha');
        }
    });

    // Formulário de listar senhas de um usuário
    const formListarSenhas = document.getElementById('formListarSenhas');
    formListarSenhas.addEventListener('submit', async (event) => {
        event.preventDefault();

        const idUsuarioListar = document.getElementById('idUsuarioListar').value;

        // Buscar senhas do usuário
        const response = await fetch(`http://localhost:8080/senhas/${idUsuarioListar}`);

        if (response.ok) {
            const senhas = await response.json();
            listarSenhas(senhas);
        } else {
            alert('Erro ao listar senhas');
        }
    });

    // Função para listar as senhas na interface
    function listarSenhas(senhas) {
        const listaSenhas = document.getElementById('listaSenhas');
        listaSenhas.innerHTML = '';

        if (senhas.length === 0) {
            listaSenhas.innerHTML = '<li>Nenhuma senha encontrada para este usuário.</li>';
        } else {
            senhas.forEach(senha => {
                const li = document.createElement('li');
                li.textContent = `Descrição: ${senha.descricao}, Senha: ${senha.hashSenha}`;
                listaSenhas.appendChild(li);
            });
        }
    }
});
