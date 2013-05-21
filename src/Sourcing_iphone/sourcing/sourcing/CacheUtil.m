//
//  CacheUtil.m
//  sourcing
//
//  Created by wuzhu_mac on 13-5-21.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import "CacheUtil.h"
#import "StringUtil.h"
#import "FileUtil.h"

@implementation CacheUtil
+(BOOL)cacheContent:(ChannelVO *)cvo content:(NSString *)kcontent
{
    if(cvo==nil||[StringUtil isEmptyStr:kcontent])
        return NO;
    NSString *filepath = [CacheUtil getChannelInfoFilePath:cvo];
    if([StringUtil isEmptyStr:filepath])
        return NO;
    NSError* error = nil;
    if(![kcontent writeToFile:filepath atomically:YES encoding:NSUTF8StringEncoding error:&error])
    {
        if(error!=nil)
        {
            NSLog(@"CacheUtil cacheContent failed:%@",error);
        }
        return NO;
    }
    return YES;
}
+(NSString *)getCacheContent:(ChannelVO *)cvo
{
    if(cvo==nil)
        return @"";
    NSString *filepath = [CacheUtil getChannelInfoFilePath:cvo];
    if([StringUtil isEmptyStr:filepath])
        return @"";
    NSError* error = nil;
    NSString *content=[NSString stringWithContentsOfFile:filepath encoding:NSUTF8StringEncoding error:&error];
    if(error!=nil)
    {
        NSLog(@"CacheUtil getCacheContent failed:%@",error);
        return @"";
    }
    return content;
}

+(NSString *)getChannelInfoFilePath:(ChannelVO *)cvo
{
    if(cvo==nil)
        return @"";
    return [FileUtil createFilePathForLocation:FileDirectoryDocuments withFileName:[NSString stringWithFormat:@"cvocache%d_%d",cvo.fatherchannelID,cvo.ChannelID] withExtension:@"dat"];
}
@end
