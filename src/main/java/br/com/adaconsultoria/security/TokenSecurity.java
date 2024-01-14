package br.com.adaconsultoria.security;

public class TokenSecurity {
/*
	/*
	 * Método utilizado para gerar o TOKEN
	 
	public static String generateToken(String email) {

		// chave secreta para geração do TOKEN (Evitar falsificações)
		String secretKey = JwtSecurity.SECRET;

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("scheduledtattoo").setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 6000000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return token;
	}

	/*
	 * Método para ler o usuário do usuário gravado no TOKEN
	 
	public static String getUserFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

		String secretKey = JwtSecurity.SECRET;
		final Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();

		return claimsResolver.apply(claims);
	}
*/
}
