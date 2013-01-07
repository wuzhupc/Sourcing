//
//  ChannelVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ChannelVO.h"
#import "ResponseVO.h"
#import "FileUtil.h"
#import "JsonParser.h"
#import "StringUtil.h"

@implementation ChannelVO
@synthesize channelName;
@synthesize fatherchannelID;
@synthesize isdefault;
@synthesize sort;
@synthesize type;
@synthesize isFirstLoad;
@synthesize lastUpdateDataTime;
@synthesize mustusertypes;


-(void)setChannelID:(NSInteger)kid
{
    _id = kid;
    channelID = kid;
}
-(NSInteger)ChannelID
{
    return channelID;
}

-(id)init
{
    self =[super init];
    if(self)
    {
        self.isFirstLoad = YES;
    }
    return self;
}

-(BOOL)isNewsChannel
{
    return fatherchannelID == CINT_CHANNELID_FATHER && type == TYPE_FATHER_NEWS;
}
-(BOOL)isPersonChannel
{
    return fatherchannelID == CINT_CHANNELID_FATHER && type == TYPE_FATHER_PERSON;
    
}
-(BOOL)isUserChannel
{
    return fatherchannelID == CINT_CHANNELID_FATHER && type == TYPE_FATHER_USER;
    
}
-(BOOL)isMoreChannel
{
    return fatherchannelID == CINT_CHANNELID_FATHER && type == TYPE_FATHER_MORE;
    
}
-(BOOL)isMore_FavChannel
{
    return fatherchannelID == TYPE_FATHER_MORE && type == TYPE_MORE_FAV;
}
-(BOOL)isMore_SettingChannel
{
    return fatherchannelID == TYPE_FATHER_MORE && type == TYPE_MORE_SETTING;
}
-(BOOL)isMore_ExitChannel
{
    return fatherchannelID == TYPE_FATHER_MORE && type == TYPE_MORE_EXIT;
}
-(BOOL)isPerson_PositionChannel
{
    return fatherchannelID == TYPE_FATHER_PERSON && type == TYPE_PERSON_POSITION;
}
-(BOOL)isPerson_TrainChannel
{
    return fatherchannelID == TYPE_FATHER_PERSON && type == TYPE_PERSON_TRAIN;
}
-(BOOL)isPerson_ProjectChannel
{
    return fatherchannelID == TYPE_FATHER_PERSON && type == TYPE_PERSON_PROJECT;
}
-(BOOL)isPerson_ResumeChannel
{
    return fatherchannelID == TYPE_FATHER_PERSON && type == TYPE_PERSON_RESUME;
}

+(NSArray *)initChannelsFromAssets
{
    NSString *channelinfo =  [FileUtil getAssetsFileContent:@"channelinfo" oftype:@"json"];
    ResponseVO *resp = [[ResponseVO alloc] init];
    return [JsonParser parseJsonToList:channelinfo respVO:&resp ref:nil];
}

+(NSArray *)getFatherChannels:(NSArray *)kchannels
{
    return [ChannelVO getChannelsWithFatherID:kchannels father:CINT_CHANNELID_FATHER];
}

+(NSArray *)getChannelsWithFatherID:(NSArray *)kchannels father:(NSInteger)kfatherid
{
    return [ChannelVO getChannels:kchannels father:kfatherid usertype:nil];
}

+(NSArray *)getChannels:(NSArray *)kchannels father:(NSInteger)kfatherid  usertype:(NSString *)kusertype
{
    if (kchannels==nil||[kchannels count]==0) {
        return nil;
    }
    NSMutableArray *result = [[NSMutableArray alloc] init];
    for (ChannelVO *vo in kchannels) {
        if([vo fatherchannelID]==kfatherid)
        {
            if ([StringUtil isEmptyStr:vo.mustusertypes]) {
                [result addObject:vo];
                continue;
            }
            //有条件要求一定要满足条件要求
            if (![StringUtil isEmptyStr:kusertype]) {
                NSRange range = [vo.mustusertypes rangeOfString:kusertype];
                if(range.location!=NSNotFound)
                    [result addObject:vo];
            }
        }
    }
    if([result count] == 0 )
        return nil;
    return result;
}

+(ChannelVO *)getChannel:(NSArray *)kchannels type:(NSInteger) ktype
{
    if(kchannels==nil||[kchannels count]==0)
        return nil;
    for (ChannelVO *vo in kchannels) {
        if (vo.type==ktype) {
            return vo;
        }
    }
    return nil;
}

@end
