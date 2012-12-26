//
//  JsonCreater.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface JsonCreater : NSObject
{
    NSMutableDictionary *_ParamsDict;
    
}
@property(nonatomic,retain) NSMutableDictionary *paramsDict;

- (void)setParam:(NSString *) name paramValue:(NSString *) value;
- (void)setParamAutoProEmpty:(NSString *) name paramValue:(NSString *) value;
- (NSString*)createJson:(NSString*)id createJsonCommandName:(NSString*) commandName;


@end


/*NSDictionary提供json支持*/
@interface NSDictionary(JSONCategories)
+(NSDictionary*)dictionaryWithContentsOfJSONURLString:(NSString*)urlAddress;
-(NSData*)toJSON;
@end
