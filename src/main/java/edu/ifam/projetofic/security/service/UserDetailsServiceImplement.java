package edu.ifam.projetofic.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.ifam.projetofic.domain.Cliente;
import edu.ifam.projetofic.enums.PerfilEnum;
import edu.ifam.projetofic.repository.ClienteRepository;
import edu.ifam.projetofic.security.domain.JwtUser;

public class UserDetailsServiceImplement implements UserDetailsService {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Cliente cliente = clienteRepository.findByCpf(username);
		
		if(cliente == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new JwtUser(cliente.getId(), cliente.getCpf(), cliente.getSenha(),
				mapToGrantedAuthority(cliente.getPerfil()));
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthority(PerfilEnum perfil) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfil.toString()));
		
		return authorities;
	}
	
}
