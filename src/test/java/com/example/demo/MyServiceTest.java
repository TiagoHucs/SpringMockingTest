package com.example.demo;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MyServiceTest {

    @Test
    void register() {
        // Cria o mock do DAO
        MyRepository myRepositoryMock = EasyMock.createMock(MyRepository.class);

        // Cria o objeto que você deseja salvar
       // MeuObjeto meuObjeto = new MeuObjeto();
        //meuObjeto.setAtributo("valor esperado"); // Defina o valor esperado para o atributo

        // Espera que o método "salvar" seja chamado com o objeto correto e captura o argumento passado
        Capture<User> userCapture = new Capture();
        myRepositoryMock.save(EasyMock.capture(userCapture));
        EasyMock.expectLastCall();

        // Finaliza a configuração do mock
        EasyMock.replay(myRepositoryMock);

        // Cria o objeto do service com o mock do DAO injetado
        MyService meuService = new MyService();

        //Injeta o mock no serviço via reflection
        try {
            Field field = meuService.getClass().getDeclaredField("myRepository");
            field.setAccessible(true);
            field.set(meuService,myRepositoryMock);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // Chama o método a ser testado
        meuService.register("Tiago Hucs",40);

        // Verifica se o método do mock foi chamado
        EasyMock.verify(myRepositoryMock);

        // Obtém o objeto capturado
        User objetoSalvo = userCapture.getValue();

        // Valida o valor do atributo no objeto salvo
        Assert.assertEquals("TIAGO HUCS", objetoSalvo.getName());
        Assert.assertEquals(Integer.valueOf(40), objetoSalvo.getAge());
        Assert.assertEquals(LocalDate.now(), objetoSalvo.getRegisterDate());
    }
}