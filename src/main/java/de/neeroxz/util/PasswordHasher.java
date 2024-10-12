package de.neeroxz.util;

/**
 * Class: PasswordHasher
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
public interface PasswordHasher {
    String hash(String password) throws Exception;
}

