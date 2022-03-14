package it.epicode.be.energy.security.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.be.energy.security.model.Role;
import it.epicode.be.energy.security.model.Roles;
import it.epicode.be.energy.security.model.User;
import it.epicode.be.energy.security.repository.RoleRepository;
import it.epicode.be.energy.security.repository.UserRepository;

@Component
public class AddUserSpringRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

		Role roleA = new Role();
		roleA.setRoleName(Roles.ROLE_ADMIN);

		Role roleU = new Role();
		roleU.setRoleName(Roles.ROLE_USER);

		User userA = new User();
		Set<Role> rolesA = new HashSet<>();
		rolesA.add(roleA);
		rolesA.add(roleU);

		User userU = new User();
		Set<Role> rolesU = new HashSet<>();
		rolesU.add(roleU);
		if (!userRepository.findByUserName("user").isPresent()) {

			userU.setNome("Mario");
			userU.setCognome("Rossi");
			userU.setUserName("user");
			userU.setPassword(bCrypt.encode("user"));
			userU.setEmail("user@usermail.com");
			userU.setRoles(rolesU);
			userU.setActive(true);
			roleRepository.save(roleU);
			userRepository.save(userU);

		}

		if (!userRepository.findByUserName("admin").isPresent()) {
			userA.setNome("Mario");
			userA.setCognome("Rossi");
			userA.setUserName("admin");
			userA.setPassword(bCrypt.encode("admin"));
			userA.setEmail("admin@domain.com");
			userA.setRoles(rolesA);
			userA.setActive(true);
			roleRepository.save(roleA);
			userRepository.save(userA);
		}
	}
}
