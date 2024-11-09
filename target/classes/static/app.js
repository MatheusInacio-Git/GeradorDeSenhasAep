document.addEventListener('DOMContentLoaded', () => {
    // Formulário de cadastro de usuário
    const formUsuario = document.getElementById('formUsuario');
    const mensagemUsuario = document.getElementById('mensagemUsuario');

    formUsuario.addEventListener('submit', async (event) => {
        event.preventDefault();

        const nomeUsuario = document.getElementById('nomeUsuario').value;
        const senhaUsuario = document.getElementById('senhaUsuario').value;

        // Verificar se a senha não está vazia
        if (!senhaUsuario || senhaUsuario.trim() === '') {
            mensagemUsuario.textContent = 'Por favor, insira uma senha.';
            mensagemUsuario.classList.add('error');
            return;
        }

        // Enviar dados para adicionar o usuário
        const response = await fetch('http://localhost:8080/usuarios', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome: nomeUsuario, senhaHash: senhaUsuario })
        });

        if (response.ok) {
            const usuario = await response.json();
            mensagemUsuario.textContent = `Usuário cadastrado com sucesso! O seu ID de usuário é ${usuario.idUsuario}.`;
            mensagemUsuario.classList.remove('error');
            formUsuario.reset();
        } else {
            mensagemUsuario.textContent = 'Erro ao cadastrar usuário';
            mensagemUsuario.classList.add('error');
        }
    });

    // Formulário de cadastro de senha
    const formSenha = document.getElementById('formSenha');
    const mensagemSenha = document.getElementById('mensagemSenha');

    formSenha.addEventListener('submit', async (event) => {
        event.preventDefault();

        const idUsuario = document.getElementById('idUsuario').value;
        const descricaoSenha = document.getElementById('descricaoSenha').value;
        const senha = document.getElementById('senha').value;
        const nome = document.getElementById('nome').value;

        // Verificar se a senha não está vazia
        if (!senha || senha.trim() === '') {
            mensagemSenha.textContent = 'Por favor, insira uma senha.';
            mensagemSenha.classList.add('error');
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
            mensagemSenha.textContent = 'Senha cadastrada com sucesso!';
            mensagemSenha.classList.remove('error');
            formSenha.reset();
        } else {
            mensagemSenha.textContent = 'Erro ao cadastrar senha';
            mensagemSenha.classList.add('error');
        }
    });

    // Formulário de listar senhas de um usuário
    const formListarSenhas = document.getElementById('formListarSenhas');
    formListarSenhas.addEventListener('submit', async (event) => {
        event.preventDefault();

        const idUsuarioListar = document.getElementById('idUsuarioListar').value;

        try {
            // Buscar informações do usuário
            const responseUsuario = await fetch(`http://localhost:8080/usuarios/${idUsuarioListar}`);
            if (!responseUsuario.ok) throw new Error('Erro ao buscar informações do usuário');
            const usuario = await responseUsuario.json();

            // Buscar senhas do usuário
            const responseSenhas = await fetch(`http://localhost:8080/senhas/${idUsuarioListar}`);
            if (!responseSenhas.ok) throw new Error('Erro ao buscar senhas do usuário');
            const senhas = await responseSenhas.json();

            listarSenhas(usuario.nome, senhas);
        } catch (error) {
            alert('Voce Não Criou esse Usuário ' + error.message);
        }
    });

    // Função para listar as senhas na interface
    function listarSenhas(nomeUsuario, senhas) {
        const listaSenhas = document.getElementById('listaSenhas');
        const nomeDoUsuario = document.getElementById('nomeDoUsuario');
        listaSenhas.innerHTML = '';
        nomeDoUsuario.textContent = `Nome do Usuário: ${nomeUsuario}`;

        if (senhas.length === 0) {
            listaSenhas.innerHTML = '<li>Nenhuma senha encontrada para este usuário.</li>';
        } else {
            senhas.forEach(senha => {
                const li = document.createElement('li');
                li.innerHTML = `
                <div><strong>Nome:</strong> ${senha.nome}</div>
                <div><strong>Descrição:</strong> ${senha.descricao}</div>
                <div><strong>Senha:</strong> ${senha.hashSenha}</div>
            `;
                listaSenhas.appendChild(li);
            });
        }
    }


});
