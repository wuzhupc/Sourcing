//
//  JsonParser.m
//  EMBAClient
//
//  Created by shan on 12-4-26.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "JsonParser.h"
#import "StringUtil.h"
#import "ResponseVO.h"

@implementation JsonParser

/**
 * 获取Json报文中的返回结果
 * @param jsonString JSON报文
*/
+ (ResponseVO* )parseJsonToResponse:(NSString *) jsonString
{
    
    ResponseVO * resVo = [[ResponseVO alloc] init];
    
    [self parseStrJson:jsonString respVO:&resVo];
	
    return resVo;
}

/**
 * 读取Json 返回报文data部分,解析失败或没有这部分内容的返回空
 * @param jsonData 报文内容
 * @param respVO 报文中的返回结果
 */
+(id) parseDataJson:(NSData *)jsonData respVO:(ResponseVO **)krespVO
{
    if(!jsonData)
        return nil;
    ResponseVO *respVO = *krespVO;
    if(!respVO)
        respVO = [[ResponseVO alloc] init];
    
    NSError *error = nil;
    NSDictionary *json = [NSJSONSerialization JSONObjectWithData:jsonData options:NSJSONReadingMutableContainers error:&error];
    
    if(error)
    {
        NSLog(@"parseDataJson error:%@",[error localizedFailureReason]);
        return nil;
    }
    
    id ret = [json valueForKey:CSTR_TAG_RET];
    
    if(!ret)
    {
        NSLog(@"parseJsonToResponse error:无效报文 %@",json);
        return nil;
    }
    
    if([ret valueForKey:@"code"])
        [respVO setCode:[[ret valueForKey:@"code"] intValue]];
    
    [respVO setMsg:[ret valueForKey:@"msg"]];
    
    return [json valueForKey:CSTR_TAG_DATA];
}

/**
 * 读取Json 返回报文data部分,解析失败或没有这部分内容的返回空
 * @param jsonStr 报文内容
 * @param respVO 报文中的返回结果
 */
+(id) parseStrJson:(NSString *)jsonStr respVO:(ResponseVO **)krespVO
{
    if ([StringUtil isEmptyStr:jsonStr]) {
        return nil;
    }
    
	NSData *jsonData = [jsonStr dataUsingEncoding:NSUTF8StringEncoding];
    
    return [self parseDataJson:jsonData respVO:krespVO];
}

/**
 * 将Josn直接转为对象
 * @param jsonStr 
 * @param respVO 报文状态
 * @param ref 特殊引用内容，如果需要获取报文中指定ref的字段，则需要对此赋值
 */
+(id) parseJsonToEntity:(NSString *)jsonStr respVO:(ResponseVO **)krespVO  ref:(NSString *)kref
{
    id data = [JsonParser parseStrJson:jsonStr respVO:krespVO];
    if(![JsonParser isEntity:data])
        return nil;
    for (id key in [data allKeys]) {
        id object = [data objectForKey:key];
        if(![JsonParser isEntity:object])
            continue;
        NSString *classstr = [JsonParser getClassOrFieldStr:key];
        if (![StringUtil isEmptyStr:kref])
        {
            NSString *tmpref = [JsonParser getRefStr:key];
            if([StringUtil isEmptyStr:tmpref]||![tmpref isEqualToString:kref])
                continue;
        }
        return [JsonParser convertToEntity:object cls:classstr];
    }
    return nil;
}

/**
 * 将Josn直接转为列表
 * @param jsonStr
 * @param respVO 报文状态
 * @param ref 特殊引用内容，如果需要获取报文中指定ref的字段，则需要对此赋值
 */
+(id) parseJsonToList:(NSString *)jsonStr respVO:(ResponseVO **)krespVO  ref:(NSString *)kref
{
    id data = [JsonParser parseStrJson:jsonStr respVO:krespVO];
    if(![JsonParser isEntity:data])
        return nil;
    for (id key in [data allKeys]) {
        id object = [data objectForKey:key];
        if(![JsonParser isList:object])
            continue;
        if (![StringUtil isEmptyStr:kref])
        {
            NSString *tmpref = [JsonParser getRefStr:key];
            if([StringUtil isEmptyStr:tmpref]||![tmpref isEqualToString:kref])
                continue;
        }
        return [JsonParser convertToList:object];
    }
    return nil;
}

+(id)convertToList:(NSArray *)array
{
    if (array==nil||[array count]==0) {
        return nil;
    }
    //TODO
    NSMutableArray *result = [NSMutableArray array];
    for (id object in array)
    {
        if ([JsonParser isProp:object]) {
            [result addObject:object];
        }else if ([JsonParser isList:object])
        {
            id list = [JsonParser convertToList:object];
            if(list!=nil)
            {
               [result addObject:list];
            }else
            {
                NSLog(@"==Error:convertToList 无法正常转换：%@",object);
            }
        }else if ([JsonParser isEntity:object])
        {
            for (id key in [object allKeys])
            {
                NSString *propname = [JsonParser getClassOrFieldStr:key];
                id subobject = [object objectForKey:key];
                if([JsonParser isProp:subobject])
                {
                    [result addObject:subobject];
                }else if([JsonParser isEntity:subobject])
                {
                    id entity = [JsonParser convertToEntity:subobject cls:propname];
                    if(entity!=nil)
                        [result addObject:entity];
                    else
                        NSLog(@"==Error:convertToList 无法正常转换key %@：%@",key,subobject);
                }
                else if([JsonParser isList:subobject])
                {
                    id list = [JsonParser convertToList:subobject];
                    if(list!=nil)
                    {
                        [result addObject:list];
                    }else
                        NSLog(@"==Error:convertToList 无法正常转换key %@：%@",key,subobject);
                    
                }else
                {
                    NSLog(@"==Error:convertToList 无法正常转换key %@：%@",key,subobject);
                }
            }
        }else
        {
            NSLog(@"==Error:convertToList 无法正常转换 %@",object);
        }
			
    }
    return result;
}

+(id)convertToEntity:(NSDictionary *)dictionary cls:(NSString *)classstr
{
    if(dictionary==nil||[dictionary count]==0||[StringUtil isEmptyStr:classstr])
        return nil;
    
    Class cls = NSClassFromString(classstr);
    
    if(cls==nil)
    {
        NSLog(@"==Error:convertToEntity 不存在类：%@", classstr);
        return nil;
    }
    
    id entity = [[cls alloc] init];
    
    for(id key in [dictionary allKeys])
    {
        NSString *propname = [JsonParser getClassOrFieldStr:key];
        NSString *refname = [JsonParser getRefStr:key];
        id object = [dictionary objectForKey:key];
        @try
        {
            if([JsonParser isProp:object])
            {
                [entity setValue:object forKey:propname];
            }
            else if([JsonParser isEntity:object])
            {
                //包含的类名_对应的属性名
                id sube = [JsonParser convertToEntity:object cls:propname];
                if(sube!=nil)
                    [entity setValue:sube forKey:refname];
                else
                    NSLog(@"==Error:convertToEntity %@对象无法对entity:%@赋值%@",classstr,key,object);
            }else if([JsonParser isList:object])
            {
                id sublist = [JsonParser convertToList:object];
                if(sublist!=nil)
                    [entity setValue:sublist forKey:propname];
                else
                    NSLog(@"==Error:convertToEntity %@对象无法对list:%@赋值%@",classstr,key,object);
            }else
            {
                NSLog(@"==Error:convertToEntity %@对象无法对%@赋值%@,未知值类型",classstr,key,object);
            }
            
        }
        @catch (NSException *e)
        {
            NSLog(@"==Error:convertToEntity %@对象无法对%@赋值%@，错误信息：%@ %@",classstr,key,object,[e name],[e reason]);
        }
        @finally {
            
        }
    }
    return entity;
}

+(BOOL)isList:(id)data
{
    if(data == nil||![data isKindOfClass:[NSArray class]]||[data count]==0)
        return NO;
    return YES;
}

+(BOOL)isProp:(id)data
{
    if(data == nil)
        return NO;
    NSArray *validClasses = @[ [NSString class], [NSNumber class], [NSNull class],[NSDate class] ];
	for (Class c in validClasses) {
		if ([data isKindOfClass:c])
			return YES;
	}
    return NO;
}

+(BOOL)isEntity:(id)data
{
    if(data == nil||![data isKindOfClass:[NSDictionary class]]||[data count]==0)
        return NO;
    return YES;
}

+(NSString *)getClassOrFieldStr:(NSString *)str
{
    if([StringUtil isEmptyStr:str])
        return @"";
    NSRange range = [str rangeOfString:@"_" ];//options:NSBackwardsSearch];
    if(range.location==NSNotFound)
        return str;
    return [str substringToIndex:range.location];
}


+(NSString *)getRefStr:(NSString *)str
{
    if([StringUtil isEmptyStr:str])
        return @"";
    NSRange range = [str rangeOfString:@"_"];
    if(range.location==NSNotFound)
        return @"";
    return [str substringFromIndex:range.location+range.length];
}

@end
