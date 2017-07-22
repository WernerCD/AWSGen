package com.wernercd.awsgen;

import java.net.URL;
import java.util.Date;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class GeneratePreSignedUrl {

    private String accessKeyId;
    public String getAccessKeyId() {
        return accessKeyId;
    }
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    private String secretKeyId;
    public String getSecretKeyId() {
        return secretKeyId;
    }
    public void setSecretKeyId(String secretKeyId) {
        this.secretKeyId = secretKeyId;
    }

    private String bucket;
    public String getBucket() {
        return bucket;
    }
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    private String file;
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }

    private int expirationInMinutes;
    public int getExpirationInMinutes() {
        return expirationInMinutes;
    }
    public void setExpirationInMinutes(int exp) {
        this.expirationInMinutes = exp;
    }

    public GeneratePreSignedUrl() {
        accessKeyId = null;
        secretKeyId = null;
        bucket = null;
        file = null;
    }

    public URL getGeneratedUrl() {
//        System.out.println("Setting up stuff");
        try {
//            System.out.println("  Creating Credentials");
            BasicAWSCredentials awsCreds
                    = new BasicAWSCredentials(getAccessKeyId(), getSecretKeyId());
            AmazonS3 s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion("us-east-1") // The first region to try your request against
//                    .withForceGlobalBucketAccess(true)
                    .build();

//            System.out.println("Generating pre-signed URL.");
//            System.out.println("  Bucket: " + getBucket());
//            System.out.println("  File: " + getFile());
//            System.out.println("  Exp: " + getExpirationInMinutes());
            URL url = GetUrl(s3Client, getBucket(), getFile(), getExpirationInMinutes());
//            System.out.println("  Result - Pre-Signed URL: " + url.toString());
            return url;
        } catch (AmazonServiceException exception) {
            System.out.println("Caught an AmazonServiceException, "
                    + "which means your request made it "
                    + "to Amazon S3, but was rejected with an error response "
                    + "for some reason.");
            System.out.println("Error Message: " + exception.getMessage());
            System.out.println("HTTP  Code: " + exception.getStatusCode());
            System.out.println("AWS Error Code:" + exception.getErrorCode());
            System.out.println("Error Type:    " + exception.getErrorType());
            System.out.println("Request ID:    " + exception.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, "
                    + "which means the client encountered "
                    + "an internal error while trying to communicate"
                    + " with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return null;
    }

    public static URL GetUrl(AmazonS3 s3client, String bucket, String file, int expirationMinutes) {
        java.util.Date expiration = getExpiration(expirationMinutes);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, file);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
        return url;
    }

    public static Date getExpiration(int expirationMinutes) {
        java.util.Date expiration = new java.util.Date();
        long milliSeconds = expiration.getTime();
        milliSeconds += 1000 * expirationMinutes * 60; // Add 1 hour.
        expiration.setTime(milliSeconds);
        return expiration;
    }
}
