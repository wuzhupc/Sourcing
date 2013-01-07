//
//  ChannelVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

#define CINT_CHANNELID_FATHER  0

enum TYPE_FATHER
{
    TYPE_FATHER_FATHER = 0,
    TYPE_FATHER_NEWS = 1,
    TYPE_FATHER_PERSON = 2,
    TYPE_FATHER_USER = 3,
    TYPE_FATHER_MORE = 4
};
enum TYPE_NEWS
{
    TYPE_NEWS_NEWEST = 0,
    TYPE_NEWS_INDUSTR = 1,
    TYPE_NEWS_POLICY = 2,
    TYPE_NEWS_NOTIFICATION = 3,
    TYPE_NEWS_EXPERT = 4
};

enum TYPE_PERSON
{
    TYPE_PERSON_NORMAL = 0,
    TYPE_PERSON_POSITION = 1,
    TYPE_PERSON_RESUME = 2,
    TYPE_PERSON_TRAIN = 3,
    TYPE_PERSON_PROJECT = 4
};

enum TYPE_MORE
{
    TYPE_MORE_NORMAL = 0,
    TYPE_MORE_FAV = 1,
    TYPE_MORE_SETTING = 2,
    TYPE_MORE_EXIT = 3
};


@interface ChannelVO : BaseVO
{
    NSInteger channelID;
    NSString *channelName;
    NSInteger fatherchannelID;
    NSInteger isdefault;
    NSInteger sort;
    NSInteger type;
    BOOL isFirstLoad;
    NSString *lastUpdateDataTime;
    NSString *mustusertypes;
}

@property NSString *channelName;
@property NSInteger fatherchannelID;
@property NSInteger isdefault;
@property NSInteger sort;
@property NSInteger type;
@property BOOL isFirstLoad;
@property NSString *lastUpdateDataTime;
@property NSString *mustusertypes;
-(void)setChannelID:(NSInteger)kid;
-(NSInteger)ChannelID;

-(BOOL)isNewsChannel;
-(BOOL)isPersonChannel;
-(BOOL)isUserChannel;
-(BOOL)isMoreChannel;

-(BOOL)isMore_FavChannel;
-(BOOL)isMore_SettingChannel;
-(BOOL)isMore_ExitChannel;

-(BOOL)isPerson_PositionChannel;
-(BOOL)isPerson_TrainChannel;
-(BOOL)isPerson_ProjectChannel;
-(BOOL)isPerson_ResumeChannel;

+(NSArray *)initChannelsFromAssets;
+(NSArray *)getFatherChannels:(NSArray *)kchannels;
+(NSArray *)getChannelsWithFatherID:(NSArray *)kchannels father:(NSInteger)kfatherid;
+(NSArray *)getChannels:(NSArray *)kchannels father:(NSInteger)kfatherid  usertype:(NSString *)kusertype;
+(ChannelVO *)getChannel:(NSArray *)kchannels type:(NSInteger) ktype;
@end
