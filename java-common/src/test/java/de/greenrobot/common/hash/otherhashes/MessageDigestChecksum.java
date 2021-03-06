/*
 * Copyright (C) 2014 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.greenrobot.common.hash.otherhashes;

import de.greenrobot.common.PrimitiveArrayUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Checksum;

/** Warpper for MessageDigest. */
public class MessageDigestChecksum implements Checksum {
    private final MessageDigest digest;
    private PrimitiveArrayUtils primitiveArrayUtils = PrimitiveArrayUtils.getInstance();

    public MessageDigestChecksum(MessageDigest digest) {
        this.digest = digest;
    }

    public MessageDigestChecksum(String algo) {
        try {
            digest = (MessageDigest.getInstance(algo));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int b) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void update(byte[] b, int off, int len) {
        digest.update(b, off, len);
    }

    @Override
    public long getValue() {
        return primitiveArrayUtils.getLongLE(digest.digest(), 0);
    }

    @Override
    public void reset() {
        digest.reset();
    }
}
