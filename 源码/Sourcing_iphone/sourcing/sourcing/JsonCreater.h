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

- (void)setParam:(NSString *) name paramValue:(id) value;
- (void)setParamNSInteger:(NSString *) name paramValue:(NSInteger) value;
- (void)setParamAutoProEmpty:(NSString *) name paramValue:(NSString *) value;
/**
 * 生成JSON语句
 * @kcommandName commandName
 */
- (NSString*)createJson:(NSString*) kcommandName;
/**
 * 生成JSON语句
 * @kid jsonid
 * @kcommandName commandName
 */
- (NSString*)createJson:(NSString*) kcommandName jsonid:(NSString*)kid;

@end


/*NSDictionary提供json支持*/
@interface NSDictionary(JSONCategories)
+(NSDictionary*)dictionaryWithContentsOfJSONURLString:(NSString*)urlAddress;
-(NSData*)toJSON;
@end
