
package aws.sdk;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.util.List;

public class AWSS3App {

    public static void main(String[] args) {

        AmazonS3 s3 = getAmazonS3Client();

        listBucket(s3);
        //createBucket(s3);
        //sendFileToS3(s3, "tiagobm-s3-sdk");
        //listObjectBucket(s3, "tiagobm-s3-sdk");
        //deleteObject(s3, "tiagobm-s3-sdk");
        deleteBucket(s3, "tiagobm-s3-sdk");
    }

    public static AmazonS3 getAmazonS3Client() {
        String accessKey = "XXXXXXXXXXXXXXXXXXXXX";
        String secretKey = "XXXXXXXXXXXXXXXXXXXXXXXX";

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,
                secretKey);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.SA_EAST_1).build();
    }

    public static void listBucket(AmazonS3 s3) {
        System.out.println("Listing buckets...");
        List<Bucket> listBuckets = s3.listBuckets();
        for (Bucket bucket : listBuckets) {
            System.out.println(bucket.getName());
        }
    }

    public static void createBucket(AmazonS3 s3){
        String bucketName = "tiagobm-s3-sdk";
        System.out.println("Creating bucket...");
        s3.createBucket(bucketName);
        System.out.println("Bucket " + bucketName + " created!");
    }

    public static void sendFileToS3(AmazonS3 s3, String bucketName){
        System.out.println("Enviando arquivo...");
        File file = new File("amazon.jpg");
        s3.putObject(bucketName, "amazon.jpg", file);
    }

    public static void listObjectBucket(AmazonS3 s3, String bucketName){
        System.out.println("Listing objects of bucket " + bucketName);

        ObjectListing listObjects = s3.listObjects(new ListObjectsRequest()
                .withBucketName(bucketName));
        for (S3ObjectSummary objectSummary : listObjects.getObjectSummaries()) {
            System.out.println("*" + objectSummary.getKey() + " - "
                    + objectSummary.getSize());
        }
    }

    public static void deleteObject(AmazonS3 s3, String bucketName){
        System.out.println("Deleting object ...");
        s3.deleteObject(bucketName, "amazon.jpg");
    }

    public static void deleteBucket(AmazonS3 s3, String bucketName){
        System.out.println("Deleting bucket ...");
        s3.deleteBucket(bucketName);
    }
}
