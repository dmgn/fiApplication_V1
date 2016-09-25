
<div class="col-sm-8">
  <md-content layout-padding>
    <div>
      <span >
       <h5 ng-style={color:'green'}>{{respMsg}}</h5>
       <h5 ng-style={color:'red'}>{{errRespMsg}}</h5>
      </span>
      <form name="userForm" style="margin-top: 5em;" ng-submit="submitForm()" novalidate>
        <div layout-gt-xs="row">
          <md-input-container class="md-block" flex-gt-sm>
            <label>Select a product</label>
            <md-select ng-model="user.refId">
              <md-option ng-repeat="product in products" value="{{product.refId}}">
                {{product.productName}}
              </md-option>
            </md-select>
          </md-input-container>
        </div>
        <div style="float:right">
          <button type="submit" class="btn btn-primary" ng-disabled="userForm.$invalid || checkRespMsg()">Submit</button>  
          <button type="button" class="btn btn-primary" ng-disabled="checkRespMsg()? false : true" ng-Click="nextAction()">Next</button>  
        </div>
      </form>
    </div>
  </md-content>
</div><!-- col-sm-8 -->
