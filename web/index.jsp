<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>AWS PreSigned URL Generator</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/scripts.js"></script>
</head>
<body>
<form name="myForm" action="response.jsp">
  <div class="form-group" class="form-horizontal">
    <label class="control-label col-sm-2" for="accessKeyId">Access Key:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control"
               id="accessKeyId" name="accessKeyId" value="" />
      </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="secretKeyId">Secret Key: </label>
      <div class="col-sm-10">
          <input type="text" class="form-control"
           id="secretKeyId" name="secretKeyId" value=""/>
      </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="bucket">Bucket: </label>
      <div class="col-sm-10">
          <input type="text" class="form-control"
           id="bucket" name="bucket" value="wernercd" />
      </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="expirationInMinutes">Expiration in Minutes: </label>
      <div class="col-sm-10">
          <input type="text" class="form-control"
           id="expirationInMinutes" name="expirationInMinutes" value="30"/>
      </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="files">Files: </label>
    <div class="col-sm-10">
      <textarea type="textarea" rows="5" class="form-control"
             id="files" name="files" >
Chico/Chico.jpg
Boone.jpg
      </textarea>
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Generate</button>
      <button type="button" onclick="storeValues(this.myForm)" class="btn btn-default">Store Cookies</button>
      <button type="button" onclick="restoreValues(this.myForm)" class="btn btn-default">Restore Cookies</button>
      <button type="button" onclick="clearValues()" class="btn btn-default">Clear Cookies</button>
    </div>
  </div>
</form>
</body>
</html>
