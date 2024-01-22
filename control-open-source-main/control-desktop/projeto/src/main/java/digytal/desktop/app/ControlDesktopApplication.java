package digytal.desktop.app;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.modulo.acesso.FrmLogin;

@SpringBootApplication()
public class ControlDesktopApplication {

	public static void main(String[] args) {
		try {
			String lf = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lf);
			Context.showSplash("Control Click - 09/11/23 12:40 - Asaas - WebHook");
			SpringApplication.run(ControlDesktopApplication.class, args);
			Context.closeSplash();
		} catch (Exception e) {
			System.exit(0);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	@Bean
	public CommandLineRunner run(FrmLogin frmLogin) throws Exception {
		return args -> {
			frmLogin.exibir();
		};
	}
}

