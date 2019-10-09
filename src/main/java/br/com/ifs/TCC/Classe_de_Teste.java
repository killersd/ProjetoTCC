package br.com.ifs.TCC;

import java.util.Scanner;
import javax.swing.JOptionPane;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class Classe_de_Teste {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("first-ksession-rule");

            FactHandle factHandler;

            //Usuário passando o sintoma
            SintomasClassificacao sintomasClassificacao = new SintomasClassificacao();

            sintomasClassificacao.setSintomas("Cefaleia");

            //sintomasClassificacao.setSintomas(JOptionPane.showInputDialog("Insira o sintoma:"));
            factHandler = kSession.insert(sintomasClassificacao);
            kSession.fireAllRules();

            //Sistema indicando: Sintoma, Classificação e  Local adequado para atendimento
            if (!(sintomasClassificacao.getClassificacao().equalsIgnoreCase("VERDE"))) {
                sintomasClassificacao.setLocal("HOSPITAL");
                JOptionPane.showMessageDialog(null, "Sintoma: " + sintomasClassificacao.getSintomas()
                        + "\nClassificação: " + sintomasClassificacao.getClassificacao() + "\nVá ao "
                        + sintomasClassificacao.getLocal() + " mais próximo");
            } else {
                sintomasClassificacao.setLocal("POSTO DE SAÚDE");
                JOptionPane.showMessageDialog(null, "Sintoma: " + sintomasClassificacao.getSintomas()
                        + "\nClassificação: " + sintomasClassificacao.getClassificacao() + "\nVá ao "
                        + sintomasClassificacao.getLocal() + " mais próximo");
            }
            kSession.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
