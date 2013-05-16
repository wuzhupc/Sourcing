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

- (void)setParam:(NSString *) name paramValue:(id) value{
    //self.mParamsDict    = [[NSDictionary alloc] init];
    if ([StringUtil isEmptyStr:name] || [StringUtil isEmpty:value]) {
        NSLog(@"paramName:%@ ? > paramValue:%@ ",name,value);
        return;
    }
    
    [self.paramsDict setValue:value forKey:name];
    
}

/**
 * 添加参数
 * @param name  auth名，如果为空,则忽略
 * @param value auth值，如果为空,则忽略
 */

- (void)setParamNSInteger:(NSString *) name paramValue:(NSInteger) value{
    
    [self.paramsDict setValue:[NSNumber numberWithInteger:value]  forKey:name];
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
 * @kcommandName commandName
 */
- (NSString*)createJson:(NSString*) kcommandName
{
    return [self createJson:kcommandName jsonid:nil];
}

/**
 * 生成JSON语句
 * @kid jsonid
 * @kcommandName commandName
 */
- (NSString*)createJson:(NSString*) kcommandName jsonid:(NSString*)kid
{
    if ([StringUtil isEmptyStr:kid]) {
        kid=[NSString stringWithFormat:@"%lli",[TimeUtil currentTimeMillis]];
    }
    
    if ([StringUtil isEmptyStr:kcommandName]) {
        return  nil;
    }
    NSMutableDictionary *jsonSubDic = [[NSMutableDictionary alloc]init];
    [jsonSubDic setObject:kid forKey:@"id"];
    [jsonSubDic setObject:kcommandName forKey:@"command"];
    
    if(self.paramsDict.count>0)
    {
        [jsonSubDic setObject:self.paramsDict forKey:@"params"];
    }
    
    if([NSJSONSerialization isValidJSONObject:jsonSubDic])
    {
        NSData *jsonData = [jsonSubDic toJSON];
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
