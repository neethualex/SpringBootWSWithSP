package com.trxade.webservice.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trxade.webservice.DO.TrxadeProduct;
import com.trxade.webservice.repository.TrxadeRepository;
import com.trxade.webservice.response.TrxadeResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/trxadeServices")
@Api(value="TrxadeStore", description="Operations pertaining to products in Trxade")
public class TrxadeController {
	/*@Autowired
	private ServiceCheckResponse serviceCheckResponse;*/
	
	@Autowired 
	private TrxadeRepository repository;
	
	@JsonSerialize
	private JSONObject entity;
	
	@Value("${service.addProduct}")
	private String addProductServiceURL;
	
	@Value("${service.updateProduct}")
	private String updateProductServiceURL;
	
	@Value("${service.deleteProduct}")
	private String deleteProductServiceURL;
	
	@Value("${service.oAuth}")
	private String oAuthServiceURL;
	
	@Value("${service.clientID}")
	private String clientId;
	
	@Value("${service.clientSecret}")
	private String clientSecret;
	
	@Value("${service.grantType}")
	private String grantType;
	
	@ApiOperation(value = "Check if the service is up and running",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully tested the service availability"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(path = "/serviceCheck", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> serviceChecker() throws JSONException {
		entity = new JSONObject();
        entity.put("ServerStatus", "Up");
        return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Quick check if the service is up and running",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully tested the service availability"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(path = "/quickCheck")
	public String quickServiceChecker() {
        return "Service Up and Running";
	}
	
	@ApiOperation(value = "View a list of available products in Trxade",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(path = "/getAllTrxadeProducts")
	public ResponseEntity<?> getAllTrxadeProducts()
	{
		List<TrxadeProduct> allProducts = repository.getAllProducts( 1,"9998", "53", "3647025", "*ALL", "", " ");
		System.out.println("TrxadeController.getAllTrxadeProducts() "+allProducts.size());
		return new ResponseEntity<>(allProducts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get product detail of one trxade Product",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(path = "/getTrxadeProductDetails/{skuNumber}")
	public ResponseEntity<?> getProductInfo(@PathVariable String skuNumber)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update all the Trxade Product at once",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(path = "/updateAllTrxadeProducts")
	public ResponseEntity<?> updateAllTrxadeProducts()
	{
		System.out.println("TrxadeController.updateAllTrxadeProducts() StartTime --- > "+LocalDateTime.now());
		List<TrxadeResponse> responseList = new ArrayList<TrxadeResponse>();
		List<TrxadeProduct> allProducts = repository.getAllProducts( 1,"9998", "53", "3647025", "*ALL", "", " ");
		int totalProducts=allProducts.size();
		OAuth2RestTemplate restTemplate = getOAuth2RestTemplate();
		//TrxadeProduct testProduct = new TrxadeProduct("NDC","615567    ","60505358306    ","Abacavir George2","APOTEX CORP","George product1","300 MG    ","60.00","28.00",new BigDecimal("1.0"),"60505358306         ","36050535836       ","ABACAVIR TAB 300MG            ","0","0",new BigDecimal("1.0"),"");
		for(int index=0;index<totalProducts;index++) {
			responseList.add(updateProduct(allProducts.get(index),restTemplate));
		//TrxadeResponse response = updateProduct(testProduct,restTemplate);
	}
		System.out.println("TrxadeController.updateAllTrxadeProducts() End Time --> "+LocalDateTime.now());
		return new ResponseEntity<>(responseList,HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Update all Trxade Product on hand information only",response = TrxadeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(path="/updateAllTrxadeProductsOnHand")
	public ResponseEntity<?> updateAllTrxadeProducts_OnHand()
	{
		/*System.out.println("TrxadeController.updateAllTrxadeProducts_OnHand() StartTime --- > "+LocalDateTime.now());
		List<TrxadeResponse> responseList = new ArrayList<TrxadeResponse>();
		List<TrxadeProductOnHand> allProducts = repository.getAllProductsOnHand( 1,"9998", "53", "3647025", "*ALL", "", " ");
		int totalProducts=allProducts.size();*/
		OAuth2RestTemplate restTemplate = getOAuth2RestTemplate();
		//TrxadeProduct testProduct = new TrxadeProduct("NDC","61556712    ","60505358306    ","Abacavir George2","APOTEX CORP","George product1","300 MG    ","60.00","28.00",new BigDecimal("1.0"),"60505358306         ","36050535836       ","ABACAVIR TAB 300MG            ","0","0",new BigDecimal("1.0"),"");
		
		TrxadeProduct testProductOnHand = new TrxadeProduct("NDC","61556712    ","60505358306    ",new BigDecimal("1.0"));
		//for(int index=0;index<totalProducts;index++) {
			//responseList.add(updateProductOnHand(allProducts.get(index),restTemplate));
		TrxadeResponse responseList = updateProductOnHand(testProductOnHand,restTemplate);
	//}
		System.out.println("TrxadeController.updateAllTrxadeProducts_OnHand() End Time --> "+LocalDateTime.now());
		return new ResponseEntity<>(responseList,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add a new Product to Trxade",response = TrxadeProduct.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value="/addTrxadeProduct", produces = "application/json")
	public ResponseEntity<?> addNewTrxadeProduct(@RequestBody @Valid TrxadeProduct product)
	{
		OAuth2RestTemplate restTemplate = getOAuth2RestTemplate();
		TrxadeResponse response = addNewProduct(product,restTemplate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete a product in Trxade",response = TrxadeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@RequestMapping(value = "/deleteTrxadeProduct/", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTrxadeProduct(@RequestBody List<String> skuNumbers)
	{
		OAuth2RestTemplate restTemplate = getOAuth2RestTemplate();
		TrxadeResponse response = deleteProducts(skuNumbers,restTemplate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete all the products in Trxade",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@PutMapping(path = "/deleteAllTrxadeProducts")
	public ResponseEntity<?> deleteAllTrxadeProducts()
	{
		List<TrxadeResponse> response=new ArrayList<TrxadeResponse>();
		List<TrxadeProduct> allProducts = repository.getAllProducts( 1,"9998", "53", "3647025", "*ALL", "", " ");
		List<String> skuNumbers = new ArrayList<String>();
		OAuth2RestTemplate restTemplate = getOAuth2RestTemplate();
		int count=0;
		for(int index=0;index<=allProducts.size();index++)
		{
			skuNumbers.add(allProducts.get(index).getSku());
			count++;
			if(count==50)
			{
				count=0;
				response.add(deleteProducts(skuNumbers,restTemplate));
			}
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update One Trxade Product",response = TrxadeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@PutMapping(path = "/updateTrxadeProduct/{skuNumber}")
	public ResponseEntity<?> updateTrxadeProduct(@PathVariable String skuNumber)
	{
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	private TrxadeResponse updateProduct(TrxadeProduct product,OAuth2RestTemplate restTemplate) {
		TrxadeResponse response = null; 
		try {
		response = restTemplate.postForObject(String.format(updateProductServiceURL),product,  TrxadeResponse.class);
		}
		catch(HttpClientErrorException ex)
		{
			response = new TrxadeResponse();
			response.setDetail(ex.getResponseBodyAsString());
			response.setStatus(String.valueOf(ex.getRawStatusCode()));
			response.setTitle(ex.getMessage());
			response.setType(ex.getStatusCode().getReasonPhrase());
			if(response.getDetail().contains("SKU_NOT_FOUND_ERROR"))
			{
				response = addNewProduct(product, restTemplate);
			}
			//errorResponse.setData(new TrxadeProductResponsePojo());
			System.out.println("Product Update Error for SKU Number "+product.getSku()+" Error " +response.getDetail());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}
	
	private TrxadeResponse updateProductOnHand(TrxadeProduct product,OAuth2RestTemplate restTemplate) {
		TrxadeResponse response = null; 
		try {
		response = restTemplate.postForObject(String.format(updateProductServiceURL),product,  TrxadeResponse.class);
		}
		catch(HttpClientErrorException ex)
		{
			response = new TrxadeResponse();
			response.setDetail(ex.getResponseBodyAsString());
			response.setStatus(String.valueOf(ex.getRawStatusCode()));
			response.setTitle(ex.getMessage());
			response.setType(ex.getStatusCode().getReasonPhrase());
			
			if(response.getDetail().contains("SKU_NOT_FOUND_ERROR"))
			{
				response = addNewProduct(product, restTemplate);
			}
			//errorResponse.setData(new TrxadeProductResponsePojo());
			System.out.println("Product Update Error for SKU Number "+product.getSku()+" Error " +response.getDetail());
			//response = errorResponse;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}
	
	
private TrxadeResponse addNewProduct(TrxadeProduct product,OAuth2RestTemplate restTemplate) {
		
		TrxadeResponse response = null; 
		try {
		response = restTemplate.postForObject(String.format(addProductServiceURL),product,  TrxadeResponse.class);
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		return response;
	}

private TrxadeResponse deleteProducts(List<String> skuNumbers,OAuth2RestTemplate restTemplate)
{
	TrxadeResponse response = null; 
	try {
	response = restTemplate.postForObject(String.format(deleteProductServiceURL),skuNumbers,  TrxadeResponse.class);
	}
	catch(Exception ex) {
		
		ex.printStackTrace();
	}
	return response;
}

private OAuth2RestTemplate getOAuth2RestTemplate()
{
	ClientCredentialsResourceDetails  resourceDetails = new ClientCredentialsResourceDetails();
	resourceDetails.setAccessTokenUri(String.format(oAuthServiceURL));
	resourceDetails.setClientId(clientId);
	resourceDetails.setClientSecret(clientSecret);
	resourceDetails.setGrantType(grantType);
	//resourceDetails.setScope(Arrays.asList("read","write"));
	
	DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
	OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails,clientContext);
	restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
	return restTemplate;
}
	

}
