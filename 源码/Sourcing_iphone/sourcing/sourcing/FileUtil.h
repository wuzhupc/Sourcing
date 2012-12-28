//
//  FileUtil.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum
{
    FileDirectoryCache,
    FileDirectoryTemp,
    FileDirectoryDocuments
} FileLocation;

@interface FileUtil : NSObject

+(NSString *)getAssetsFileContent:(NSString *)filename oftype:(NSString *)type;
+ (NSURL *)createFileURLForLocation:(FileLocation)location withFileName:(NSString *)filename withExtension:(NSString *)extension;
+ (NSString *)createFilePathForLocation:(FileLocation)location withFileName:(NSString *)filename withExtension:(NSString *)extension;
@end
