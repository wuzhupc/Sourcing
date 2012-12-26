//
//  JsonCreater.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "JsonCreater.h"
#import "StringUtil.h"
#import "TimeUtil.h"


@implementation JsonCreater
@synthesize paramsDict = _ParamsDict;


- (id) init
{
    self = [super init];
    if(self)
    {
        self.paramsDict    = [[NSMutableDictionary alloc] init];
    }
    return self;
}


/**
 * 添加参数
 * @param name  auth名，如果为空,则忽略
 * @param value auth值，如果为空,则忽略
 */

- (void)setParam:(NSString *) name paramValue:(NSString *) value{
    //self.mParamsDict    = [[NSDictionary alloc] init];
    if ([StringUtil isEmptyStr:name] || [StringUtil isEmptyStr:value]) {
        NSLog(@"paramName:%@ ? > paramValue:%@ ",name,value);
        return;
    }
    
    [self.paramsDict setValue:value forKey:name];
    
}
/**
 * 添加参数
 * @param name  auth名，如果为空,则忽略
 * @param value auth值，如果为空,则用空字符串代替值
 */
- (void)setParamAutoProEmpty:(NSString *) name paramValue:(NSString *) value
{
    if ([StringUtil isEmptyStr:name]) {
        NSLog(@"paramName:%@ ? > paramValue:%@ ",name,value);
        return;
    }
    
    if([StringUtil isEmptyStr:value])
        [self.paramsDict setValue:@"" forKey:name];
    else
        [self.paramsDict setValue:value forKey:name];
}


/**
 * 生成JSON语句
 * @createJsonID jsonid
 * @createJsonID commandName
 */

- (NSString*)createJson:(NSString*)jsonid createJsonCommandName:(NSString*) commandName
{
    if ([StringUtil isEmptyStr:jsonid]) {
        jsonid=[NSString stringWithFormat:@"%lli",[TimeUtil currentTimeMillis]];
    }
    
    if ([StringUtil isEmptyStr:commandName]) {
        return  nil;
    }
    NSMutableDictionary *jsonRootDic = [[NSMutableDictionary alloc]init];
    NSMutableDictionary *jsonSubDic = [[NSMutableDictionary alloc]init];
    [jsonSubDic setObject:jsonid forKey:@"id"];
    [jsonSubDic setObject:commandName forKey:@"command"];
    
    if(self.paramsDict.count>0)
    {
        [jsonSubDic setObject:self.paramsDict forKey:@"params"];
    }
    
    [jsonRootDic setObject:jsonSubDic forKey:@"newsreader_request"];
    
    if([NSJSONSerialization isValidJSONObject:jsonRootDic])
    {
        NSData *jsonData = [jsonRootDic toJSON];
        if(jsonData != nil)
            return [[NSString alloc] initWithData:jsonData
                                         encoding:NSUTF8StringEncoding];
    }
    return nil;
    
}

@end

@implementation NSDictionary(JSONCategories)
+(NSDictionary*)dictionaryWithContentsOfJSONURLString:(NSString*)urlAddress
{
    NSData* data = [NSData dataWithContentsOfURL:
                    [NSURL URLWithString: urlAddress] ];
    __autoreleasing NSError* error = nil;
    id result = [NSJSONSerialization JSONObjectWithData:data
                                                options:kNilOptions error:&error];
    if (error != nil) return nil;
    return result;
}

-(NSData*)toJSON
{
    NSError* error = nil;
    id result = [NSJSONSerialization dataWithJSONObject:self
                                                options:kNilOptions error:&error];
    if (error != nil) return nil;
    return result;
}
@end
