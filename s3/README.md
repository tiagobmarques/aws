# List of commands to interact with AWS CLI

Create a bucket
```
aws s3 mb s3://tiagobmtest
```

List buckets
```
aws s3 ls
```

Remove a bucket
```
aws s3 rb s3//:tiagobmtest
```

List the content of the bucket
```
aws s3 ls s3://tiagobmtest
```

Force remove of bucket and files
```
aws s3 rb s3//:tiagobmtest --force
```

Copy the file bucket.png from S3 to dir locally
```
aws s3 cp s3://tiagobmtest/bucket.png . 
```

Copy the file test.txt from locally to s3 
```
aws s3 cp test.txt s3://tiagobmtest
```

Remove a file
```
aws s3 rm s3://tiagobmtest/test.txt
```

Rename file
```
aws s3 mv s3://tiagobmtest/test.txt s3://tiagobmtest/test_1.txt
```

Sync a bucket to dir alura-s3
```
aws s3 sync s3://tiagobmtest alura-s3
```

Sync the folder to bucket in the S3
```
aws s3 sync . s3://alura-s3 //-- Sin pasta local para S3
```

Sync the folder with deleted files to bucket in the S3
```
aws s3 sync . s3://alura-s3 --delete //-- Sincronizaçao com objetos deletados
```