
<div id="example">
    <div style="padding-top: 1em;" class="demo-section k-content" ng-controller="RegistrationDocsController">
        
        <!-- <div style="padding-top: 1em; padding-bottom: 1em;">
            <h4>Select a category </h4>
            <select id="customers"
            kendo-drop-down-list style="width: 50%"
            k-options="" ng-model="selectedValue">
                <option value="IDPROOF">Id Proof</option>
                <option value="ADDRESSPROOF">Address Proof</option>
                <option value="TAXRETURNS">Tax Returns</option>
                <option value="OTHERS">Others</option>
            </select>
        </div> -->
        <div class="well" style="padding-top: 1em;">
            <h4>Upload Document Files </h4>
               <!-- <h4>Select a category </h4> -->
            <span>Select a category : </span>
            <select id="customers"
            kendo-drop-down-list 
            ng-model="selectedValue">
                <option value="IDPROOF" selected>Id Proof</option>
                <option value="ADDRESSPROOF">Address Proof</option>
                <option value="TAXRETURNS">Tax Returns</option>
                <option value="OTHERS">Others</option>
            </select><br/><br/>
            <input name="file"
                   type="file"
                   kendo-upload
                   k-async='{ 
                   saveUrl: "/floatinvoice/register/upload", 
                   autoUpload: true }'
                   k-select="onSelect"
                   />
        </div>
        <div style="float:right">           
<!--             <button type="button" class="btn btn-primary" ng-disabled="checkRespMsg()? false : true" ng-Click="nextAction()">Next</button> -->  
<!--          <button type="button" class="btn btn-primary" ng-Click="nextAction()">Next</button>
 -->        </div>
        <div style="padding-top: 1em;">
            <h4> File list </h4>
            <table class="table table-striped">
                <tr>
                  <th><a href="" ng-click="sortField = 'fileName'">Name</td>
                  <th><a href="" ng-click="sortField = 'timest'">Upload Time</th>
                  <th><a href="" ng-click="sortField = 'categ'">Category</th>
                  <th><a href="" ng-click="sortField = 'user'">User</th>
                </tr>                
                <tr ng-repeat="file in fileList | filter:search | orderBy:sortField">
                    <!-- <td><a href ng-click="openFile(file)">{{file.fileName}}</a></td> -->
                    <td><a ng-href="{{getUrl(file)}}">{{file.fileName}}</a></td>  


                    <td>{{file.timest}}</td>
                    <td>{{file.categ}}</td>
                    <td>{{file.user}}</td>
                </tr>
            </table>
          <!-- <div align="center">
            <dir-pagination-controls/>
          </div> -->


        </div>

            <div style="padding-top: 1em;">
            <h4> Enquiry list </h4>
            <table class="table table-striped">
             <tr>
                  <th></th>
                  <th><a href="" ng-click="sortField = 'fileName'">Contact Name</th>
                  <th><a href="" ng-click="sortField = 'timest'">Enquiry Date</th>
                  <th><a href="" ng-click="sortField = 'categ'">Email</th>
                  <th><a href="" ng-click="sortField = 'user'">Status</th>
                </tr>  
                <tr ng-repeat="enquiry in enquiryList | filter:search | orderBy:sortField">
                    <!-- <td><a href ng-click="openFile(file)">{{file.fileName}}</a></td> -->
                    <!--<a ng-href="{{getUrl(file)}}">-->
                   <!--  <td><input type="radio" name="refId" ng-value="{{enquiry.refId}}" ng-model="$parent.refId"/></td> -->
                   <td><input type="radio" name="enquiry" ng-value="enquiry.refId" ng-model="$parent.selectedoption"></td>
                    <td>{{enquiry.contactName}}</a></td>  
                    <td>{{enquiry.enqDate}}</td>
                    <td>{{enquiry.email}}</td>
                    <td>{{enquiry.enqStatus}}</td>
                    
                </tr>

            </table>

        </div>
        <div align="center">   
           
          <button type="button" class="btn btn-primary" ng-disabled="optionSelected()" ng-Click=notifyFI()>Notify Float Invoice</button>
            

        </div>

    </div>
<!-- removeUrl: 'remove' -->
</div>