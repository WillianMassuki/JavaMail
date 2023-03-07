package br.com.ProjetoEnvioEmail.repositorys;

import br.com.ProjetoEnvioEmail.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
