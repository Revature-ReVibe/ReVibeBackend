package com.ReVibe.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service @Slf4j
public class AccountService {
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account findByUserId(int id) {
		log.info("find user id {} in Database", id);
		return this.accountRepository.findByUserId(id);
	}


	public List<Account> findAll(){
		log.info("find all users");
		return this.accountRepository.findAll();
	}

	public Account findByName(String name) {
		log.info("find {} in Database", name);
		return this.accountRepository.findByName(name);
	}
	

	public void merge(Account account) {
		log.info("Merge {} ", account);
		this.accountRepository.setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getProfilePic(),account.getEmail());
	}


	public List<Account> findBySearchName(String name) {
		log.info("Search {} in Database", name);
		return this.accountRepository.findByNameContaining(name);
	}

 


	public Account saveAccount(Account account) {
		log.info("saving account {} in Database", account);
		return this.accountRepository.saveAccount(account);
	}

	public Account findByUsernameAndPassword(String username, String password) {
		log.info("find user {} and password{}", username, password);
		Account user = this.accountRepository.findByUsernameAndPassword(username, password);
		if (user == null) {
			return null; 
			
		}
		else
			return user;
	}

	public Account findByEmail(String email) {
		log.info("find user by {} in Database", email);
		return this.accountRepository.findByEmail(email);
<<<<<<< HEAD
	}
=======
	}
	
	// ----------------------------------------------------------------------------------------------------------------
    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
	
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        System.out.println("byte[] = " + apiKeySecretBytes);
        
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer).signWith(signatureAlgorithm, signingKey);
        
        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        
        System.out.println("compact:" + builder.compact());
        System.out.println("the full obj?:" + builder);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
	}
	
	public static Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
		Claims claim = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).parseClaimsJws(jwt).getBody();
		
		return claim;
	}


	

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void save(Account account) {
		log.info("Saving new user {} to the database", account.getName());
		this.accountRepository.save(account);
	}

	// TODO: FOR KWAME TO FIX
//	public Account findByUsername(String username) {
//		log.info("{} found in the database", username);
//		return this.accountRepository.findByUsername(username);
//	}
>>>>>>> parent of 6ab81fe (modified the login method just a tiny bit so that it can be parsed easier on the front end)

}

