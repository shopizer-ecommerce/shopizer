package com.salesmanager.shop.store.api.v1.tax;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**'
 * Tax class management
 * @author carlsamson
 *
 */

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "Tax management resource (Tax Management Api)" })
@SwaggerDefinition(tags = {
		@Tag(name = "Tax management resource", description = "Manage tax classes and tax rates") })
public class TaxClassApi {

}
