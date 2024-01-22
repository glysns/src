package digytal.desktop.app.context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.components.desktop.Splash;
import digytal.desktop.components.model.Empresa;
import digytal.desktop.components.model.Sessao;
import digytal.desktop.components.model.Usuario;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Context {
    private static ApplicationContext application;
    private static Sessao sessao;
    private static Splash splash;

    @Autowired
    public Context(ApplicationContext application, Sessao sessao) {
        Context.application = application;
        Context.sessao = sessao;
    }
    public static <T> T getBean(Class classe) {
        return (T) application.getBean(classe);
    }
    public static void showSplash() {
        showSplash("Digytal Versão 1.0");
    }
    public static void showSplash(String slogan) {
        splash = new Splash();
        splash.show(slogan);
    }
    public static void closeSplash() {
        if (splash != null) {
            splash.setVisible(false);
            splash.dispose();
        }
    }
    public static Sessao getSessao() {
        return sessao;
    }
    public static Usuario getUsuario() {
        return sessao.getUsuario();
    }
    public static Integer getUsuarioId() {
        return getUsuario().getId();
    }
    public static Empresa getEmpresa() {
        return getSessao().getEmpresa();
    }
    public static Integer getEmpresaId() {
        return getEmpresa().getId();
    }
    public static Integer getOrganizacaoId() {
        return getEmpresa().getOrganizacao();
    }
}
